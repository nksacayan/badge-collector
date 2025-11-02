import './Leaderboard.css';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router';
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;


const Leaderboard = () => {
	const [users, setUsers] = useState([]);
	const navigate = useNavigate();


	useEffect(() => {
		const fetchData = async () => {
			const allUsersResponse = await fetch(`${apiUrl}/users`);
			if (!allUsersResponse.ok) throw new Error(`HTTP error! status: ${allUsersResponse.status}`);
			const allUsers = await allUsersResponse.json();
			setUsers(allUsers);
		};

		fetchData();
	}, []);

	return (
		<div className='common-flex-container content-top'>
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
					{users
						.sort((a, b) => b.badges.length - a.badges.length)
						.map((user, index) => (
						<tr key={user.id || index}>
							<td>{index + 1}. {user.name}</td>
							<td>{user.badges.length}</td>
						</tr>
						))}
					</tbody>
			</table>
		</div>
	);
};

export default Leaderboard;