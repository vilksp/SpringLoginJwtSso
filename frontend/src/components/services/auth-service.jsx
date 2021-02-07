import axios from "axios";
import cookies from "js-cookie";

export const login = (details) => {
	try {
		axios
			.post("http://localhost:8080/api/user/login", details)
			.then((res) => storeToken(res.data.jwt))
			.then(console.log(getToken));
	} catch (err) {
		console.log(err);
	}
};

const storeToken = (token) => {
	if (token !== "") {
		cookies.set("JWT", token);
	}
};

export const getToken = () => {
	const jwt_token = cookies.get("JWT");
};
