import React, { Component, useEffect } from "react";
import { isTokenValid } from "./services/auth-service";

function Homepage() {
	useEffect(() => {
		if (!isTokenValid()) {
			window.location.replace("/login");
		}
	});

	return (
		<div>
			<h1>Your logged in</h1>
		</div>
	);
}
export default Homepage;
