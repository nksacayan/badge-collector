import { Link } from 'react-router';
import BadgeCard from '../components/BadgeCard';
import './BadgeGallery.css';
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../components/UserContext";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;


const BadgeGallery = () => {
	const context = useContext(UserContext);
	const { user, setUser } = context;
	const [badges, setBadges] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const allBadgeResponse = await fetch(`${apiUrl}/badges`);
				if (!allBadgeResponse.ok) throw new Error(`HTTP error! status: ${allBadgeResponse.status}`);
				const allBadges = await allBadgeResponse.json();

				const userResponse = await fetch(`${apiUrl}/user/${user.name}`);
				if (!userResponse.ok) throw new Error(`HTTP error! status: ${userResponse.status}`);
				const userJson = await userResponse.json();
				setUser(userJson);

				// Create a Set of user badge IDs for fast lookup
				const ownedBadgeIds = new Set(userJson.badges.map(b => b.id));

				// Merge, annotate, and filter
				const combinedBadges = allBadges
					.map(badge => ({
						...badge,
						owned: ownedBadgeIds.has(badge.id),
					}))
					.filter(badge => !badge.secret || badge.owned);

				setBadges(combinedBadges);
			} catch (err) {
				console.error('Failed to fetch badges:', err);
			}
		};

		fetchData();
	}, []);

	return (
		<div className='common-flex-container content-top'>
			<h2 className="badge-welcome uppercase">Welcome, {user.name}</h2>

			<div className='badge-headers-container'>
				<h3 className="badge-header uppercase">Your Badges</h3>
				<Link to="/leaderboard" className="leaderboard-button common-button">Leaderboard &gt;</Link>
				<p className="badge-instruction">Click badge for details!</p>
			</div>

			<div className="badge-grid">
				{badges.map(badge => (
					<BadgeCard key={badge.id} badge={badge} />
				))}
			</div>
		</div>
	);
};

export default BadgeGallery;