package com.seolandfriends.byeolbyeolcoffee.user;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;

@SpringBootTest
public class UserLoginTest {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 사용자 서비스 또는 리포지토리를 주입받습니다. (예시로 작성된 코드입니다)
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testLoginWithUsernameAndPassword() {
		// 가정: 사용자의 아이디와 원본 비밀번호
		String userAccount = "user123";
		String rawPassword = "hashed_password";

		// 사용자 아이디로 사용자 정보를 조회합니다. (예시로 작성된 코드입니다)
		User user = userRepository.findByUserAccount(userAccount);

		// 사용자 정보가 데이터베이스에 있는지 확인
		assertThat(user).isNotNull();

		// 데이터베이스에 저장된 해시된 비밀번호를 가져옵니다.
		String storedHashedPassword = user.getUserPassword(); // getPassword()는 사용자의 비밀번호 해시 값을 반환하는 메소드입니다.

		// 로그인 시도 시 사용자가 입력하는 비밀번호와 데이터베이스에 저장된 해시를 비교하여 검증합니다.
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(rawPassword, storedHashedPassword);

		assertThat(isPasswordMatch).isTrue();
	}
}
