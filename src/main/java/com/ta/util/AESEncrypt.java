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
package com.ta.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ta.dto.ErrorLogDto;
import com.ta.enumeration.LogOperation;

@Service
@Component
public class AESEncrypt {

	private static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for (byte b : key.getBytes(encoding))
				finalKey[i++ % 16] ^= b;
			return new SecretKeySpec(finalKey, "AES");
		} catch (UnsupportedEncodingException e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE).errorMessage(e.getMessage())
					.exception(e).build());
		}
		return null;
	}

	public static String encrypt(String text, String secretKey) {
		if (text != null && !text.isEmpty()) {
			Cipher encryptCipher = null;
			String encryptedText = null;
			try {
				encryptCipher = Cipher.getInstance("AES");
				encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(secretKey, "UTF-8"));
				encryptedText = new String(Hex.encodeHex(encryptCipher.doFinal(text.getBytes(StandardCharsets.UTF_8))));
			} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException
					| NoSuchPaddingException e) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE)
						.errorMessage(e.getMessage()).exception(e).build());
			}

			return encryptedText;
		}
		return null;
	}

	public static String decrypt(String text, String secretKey) {
		String decryptedText = null;
		try {
			final Cipher decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(secretKey, "UTF-8"));
			decryptedText = new String(decryptCipher.doFinal(Hex.decodeHex(text.toCharArray())));
		} catch (DecoderException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().additionalInfo("DecodeError")
					.operation(LogOperation.SELECT).errorMessage(e.getMessage()).build());
		}
		return decryptedText;
	}

}
