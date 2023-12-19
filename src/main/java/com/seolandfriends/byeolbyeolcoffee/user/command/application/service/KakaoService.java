package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoService {

	private static final Logger LOGGER = Logger.getLogger(KakaoService.class.getName());

	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=99902aa48e763fcf38fec71b9dbb33d2"); //본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:3000/login/oauth/kakao/callback"); // 본인이 설정한 주소
			sb.append("&code=" + authorize_code);

			bw.write(sb.toString());
			bw.flush();

			int responseCode = conn.getResponseCode();
			LOGGER.info("Response code from Kakao API: " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			LOGGER.info("Response body from Kakao API: " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			LOGGER.info("Access Token: " + access_Token);
			LOGGER.info("Refresh Token: " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			LOGGER.severe("IOException occurred in getAccessToken: " + e.getMessage());
			e.printStackTrace();
		}

		return access_Token;
	}

	public HashMap<String, Object> getUserInfo(String access_Token) {
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			LOGGER.info("Response code from Kakao API (getUserInfo): " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			LOGGER.info("Response body from Kakao API (getUserInfo): " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonElement kakaoAccountElement = element.getAsJsonObject().get("kakao_account");
			JsonObject kakao_account = kakaoAccountElement != null && kakaoAccountElement.isJsonObject()
				? kakaoAccountElement.getAsJsonObject()
				: null;

			String kakaoEmail = kakao_account != null && kakao_account.has("email")
				? kakao_account.get("email").getAsString()
				: null;
			String kakaoName = kakao_account != null && kakao_account.has("profile")
				&& kakao_account.getAsJsonObject("profile").has("nickname")
				? kakao_account.getAsJsonObject("profile").get("nickname").getAsString()
				: null;

			userInfo.put("kakaoEmail", kakaoEmail);
			userInfo.put("kakaoName", kakaoName);
			JsonElement propertiesElement = element.getAsJsonObject().get("properties");
			JsonObject properties = propertiesElement != null && propertiesElement.isJsonObject()
				? propertiesElement.getAsJsonObject()
				: null;

			if (kakao_account != null) {
				String userEmail = kakao_account.has("email") ? kakao_account.get("email").getAsString() : null;
				String userNickName = properties.has("nickname") ? properties.get("nickname").getAsString() : null;
				String userAccount = userNickName; // 혹은 다른 정보를 userAccount에 저장

				if (userEmail != null) userInfo.put("userEmail", userEmail);
				if (userNickName != null) userInfo.put("userNickName", userNickName);
				if (userAccount != null) userInfo.put("userAccount", userAccount);
			}

		} catch (IOException e) {
			LOGGER.severe("IOException occurred in getUserInfo: " + e.getMessage());
			e.printStackTrace();
		}

		return userInfo;
	}
}
