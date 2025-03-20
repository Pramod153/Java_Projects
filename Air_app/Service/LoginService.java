package com.air_app.service;

import java.util.Scanner;

import com.air_app.Payload.LoginResponse;

public interface LoginService {
	LoginResponse login(Scanner sc);
}
