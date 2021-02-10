import axios from "axios";
import Cookies from "js-cookie";
import jwt_decode from "jwt-decode";

export const login = (details) => {
	try {
		axios
			.post("http://localhost:8080/api/user/login", details)
			.then((res) => storeToken(res.data.jwt));
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
