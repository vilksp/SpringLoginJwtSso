import axios from "axios";
import Cookies from "js-cookie";

export const login = (details) => {
	try {
		axios
			.post("http://localhost:8080/api/user/login", details)
			.then((res) => storeToken(res.data.jwt));
	} catch (err) {
		console.log(err);
	}
};

export const storeToken = (token) => {
	if (token !== "") {
		Cookies.set("JWT", token);
	}
};
