import axios from "axios";
import Cookies from "js-cookie";
import jwt_decode from "jwt-decode";
import { Redirect } from "react-router";

export const login = (details) => {
	try {
		axios
			.post("http://localhost:8080/api/user/login", details)
			.then((res) => storeToken(res.data.jwt))
			.then(() => window.location.replace("/"));
	} catch (err) {
		console.log(err);
	}
};

const storeToken = (token) => {
	if (token !== "") {
		Cookies.set("JWT", token);
	}
};

export const isTokenValid = () => {
	try {
		const jwt_token = Cookies.get("JWT");

		if (jwt_token === "") {
			return false;
		} else {
			const decoded = jwt_decode(jwt_token);

			if (Date.now() >= decoded.exp * 1000) {
				return false;
			}
			return true;
		}
	} catch (error) {
		console.log(error);
	}
};

export const logout = () => {
	Cookies.remove("JWT");
};

export const registerNewUser = (details) => {
	try {
		axios
			.post("http://localhost:8080/api/user/register", details)
			.then(() => window.location.replace("/login"));
	} catch (err) {
		console.log(err);
	}
};
