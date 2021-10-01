/*******************************************************************************
 * ======================================================================================================
 *                                     Copyright (C) 2019 Trackerwave Pvt Ltd.
 *                                             All rights reserved
 * ======================================================================================================
 * Notice:  All Rights Reserved.
 * This material contains the trade secrets and confidential business information of Trackerwave Pvt Ltd,
 * which embody substantial creative effort, design, ideas and expressions.  No part of this material may
 * be reproduced or transmitted in any form or by any means, electronic, mechanical, optical or otherwise
 * ,including photocopying and recording, or in connection with any information storage or retrieval
 * system, without written permission.
 *        
 * www.trackerwave.com, Traceability and Change log maintained in Source Code Control System
 * =====================================================================================================
 ******************************************************************************/
package com.ta.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.ta.dto.ErrorLogDto;
import com.ta.dto.IBasicModel;
import com.ta.dto.JwtTokenDto;
import com.ta.dto.LoginDto;
import com.ta.dto.ResourceDto;
import com.ta.dto.TokenDto;
import com.ta.entity.User;
import com.ta.entity.model.RoleModel;
import com.ta.entity.model.UserModel;
import com.ta.enumeration.LogOperation;
import com.ta.repository.ResourceRepository;
import com.ta.repository.UserRepository;
import com.ta.util.Constants;
import com.ta.util.LogWrapper;
import com.ta.util.URLUtil;

@Service
public class OauthTokenService {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	@Value("${oauth.login-url}")
	private String url;

	@Autowired
	private URLUtil urlUtil;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ResourceRepository resourceRepo;

	public TokenDto login(LoginDto loginDto) {
		TokenDto tokenDto = oauthLogin(loginDto);
		if (tokenDto != null) {
			Optional<User> userOp = userRepo.findByEmail(loginDto.getUsername());
			if (userOp.isPresent()) {
				User user = userOp.get();
				Long userId = user.getId();
				UserModel userModel = new UserModel();
				userModel.setId(userId);
				userModel.setAddress(user.getAddress());
				userModel.setDob(user.getDob());
				userModel.setEmail(user.getEmail());
				userModel.setFirstName(user.getFirstName());
				userModel.setLastName(user.getLastName());
				userModel.setMobileNumber(user.getMobileNumber());

				List<IBasicModel> roles = userRepo.getAllUserRoles(userId);
				List<Long> roleIds=roles.stream().map(r->r.getId()).collect(Collectors.toList());

				Set<RoleModel> rolModels = new HashSet<>();

				for (IBasicModel bm : roles) {
					RoleModel rm = new RoleModel();
					rm.setId(bm.getId());
					rm.setName(bm.getName());
					rolModels.add(rm);
				}

				userModel.setRoles(rolModels);
				tokenDto.setUser(userModel);

				Set<ResourceDto> allResoures = resourceRepo.getUserResources(userId,roleIds);
				Set<ResourceDto> resoures = allResoures.stream().filter(al -> al.getParentId() == null)
						.collect(Collectors.toSet());

				for (ResourceDto res : resoures) {
					Set<ResourceDto> subRes = allResoures.stream().filter(al -> al.getParentId() != null)
							.filter(al -> al.getParentId().equals(res.getId())).collect(Collectors.toSet());
					res.setSubMenus(subRes);

					for (ResourceDto res2 : subRes) {
						Set<ResourceDto> subRes2 = allResoures.stream().filter(al -> al.getParentId() != null)
								.filter(al -> al.getParentId().equals(res2.getId())).collect(Collectors.toSet());
						res2.setSubMenus(subRes2);
					}
				}

				Map<String, Object> permissionMap = new HashMap<>();
				permissionMap.put("menuItems", resoures);
				tokenDto.setPermissions(permissionMap);
			}
		}
		return tokenDto;
	}

	private TokenDto oauthLogin(LoginDto loginDto) {
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		JwtTokenDto pojoObjList = null;
		try {

			String basicBase64format = "Basic "
					+ Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", basicBase64format);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", "password");
			map.add("username", username);
			map.add("password", password);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			ResponseEntity<JwtTokenDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
					JwtTokenDto.class);
			pojoObjList = responseEntity.getBody();

		} catch (HttpStatusCodeException e) {
			e.printStackTrace();
		}

		TokenDto tokenDto = null;
		if (pojoObjList != null) {
			tokenDto = new TokenDto();
			tokenDto.setAccessToken(pojoObjList.getAccessToken());
			tokenDto.setRefreshToken(pojoObjList.getRefreshToken());
			tokenDto.setExpiresIn(pojoObjList.getExpiresIn() + "");
		}
		return tokenDto;
	}

	public TokenDto refreshNewToken(String refreshToken, HttpServletRequest request) {

		TokenDto tokenDto = null;
		Map pojoObjList = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			String basicBase64format = "";

			basicBase64format = "Basic "
					+ Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

			headers.add("Authorization", basicBase64format);

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type", Constants.HeaderValue.REFRESH_TOKEN);
			map.add(Constants.HeaderValue.REFRESH_TOKEN, refreshToken);

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
			pojoObjList = responseEntity.getBody();

		} catch (HttpStatusCodeException ex) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().additionalInfo(refreshToken).operation(LogOperation.CREATE)
					.errorMessage(ex.getMessage()).exception(ex).build());
		}

		if (pojoObjList != null) {
			tokenDto = new TokenDto();
			tokenDto.setAccessToken((String) pojoObjList.get("access_token"));
			tokenDto.setRefreshToken((String) pojoObjList.get(Constants.HeaderValue.REFRESH_TOKEN));
			tokenDto.setExpiresIn(pojoObjList.get("expires_in") + "");
		}

		return tokenDto;
	}
}
