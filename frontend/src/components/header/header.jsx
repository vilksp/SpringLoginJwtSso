import React, { useEffect } from "react";
import { isTokenValid } from "../services/auth-service";

const Header = () => {
	return (
		<div>
			<div className="header">{isTokenValid()}</div>
		</div>
	);
};
export default Header;
