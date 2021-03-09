import "./register.css";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { isTokenValid, registerNewUser } from "../services/auth-service";

function Register() {
	const [details, setDetails] = useState({
		username: "",
		email: "",
		firstName: "",
		lastName: "",
		password: "",
	});

	const submitHandler = (e) => {
		if (
			details.username !== "" &&
			details.password !== "" &&
			details.email !== "" &&
			details.firstName !== "" &&
			details.lastName !== ""
		) {
			registerNewUser(details);
		}
	};
	useEffect(() => {
		if (isTokenValid()) {
			window.location.replace("/");
		}
	});
	return (
		<div className="login-box">
			<h1>Register</h1>
			<div className="text-box">
				<input
					type="username"
					placeholder="Username"
					value={details.username}
					onChange={(e) => setDetails({ ...details, username: e.target.value })}
				></input>
			</div>
			<div className="text-box">
				<input
					type="email"
					placeholder="Email"
					value={details.email}
					onChange={(e) => setDetails({ ...details, email: e.target.value })}
				></input>
			</div>
			<div className="text-box">
				<input
					type="text"
					placeholder="FirstName"
					value={details.firstName}
					onChange={(e) =>
						setDetails({ ...details, firstName: e.target.value })
					}
				></input>
			</div>
			<div className="text-box">
				<input
					type="text"
					placeholder="LastName"
					value={details.lastName}
					onChange={(e) => setDetails({ ...details, lastName: e.target.value })}
				></input>
			</div>
			<div className="text-box">
				<input
					type="password"
					placeholder="Password"
					value={details.password}
					onChange={(e) => setDetails({ ...details, password: e.target.value })}
				></input>
			</div>
			<input
				className="btn"
				type="button"
				value="Sign in"
				onClick={submitHandler}
			/>

			<Link to="/login">
				<button className="btn">Already have account? Login here</button>
			</Link>
		</div>
	);
}

export default Register;
