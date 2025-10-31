import { Link } from 'react-router';
import './Leaderboard.css';
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../components/UserContext";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;


const Leaderboard = () => {
	const context = useContext(UserContext);
	const { user } = context;
	const [badges, setBadges] = useState([]);

	useEffect(() => {
		const fetchData = async () => {

		};

		fetchData();
	}, []);

	return (
		<div className="leaderboard-container">
			<button className="back-button common-button" onClick={() => navigate('/badges')}>Back</button>
			<h1 className="leaderboard-header">LEADERBOARD</h1>
			<table border="1">
			<thead>
				<tr>
					<th>Player</th>
					<th>Badges</th>
				</tr>
			</thead>
				<tbody>
					<tr>
						<td>Alice</td>
						<td>30</td>
					</tr>
					<tr>
						<td>Bob</td>
						<td>25</td>
					</tr>
				</tbody>
			</table>
		</div>
	);
};

export default Leaderboard;