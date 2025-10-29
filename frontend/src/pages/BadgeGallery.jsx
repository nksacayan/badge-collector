import { Link } from 'react-router';
import BadgeCard from '../components/BadgeCard';
import './BadgeGallery.css';
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../components/UserContext";
const apiUrl = import.meta.env.VITE_BACKEND_API_URL;


const BadgeGallery = () => {
	const context = useContext(UserContext);
	const { user } = context;
	const [badges, setBadges] = useState([]);

useEffect(() => {
	const fetchData = async () => {
		try {
			const response = await fetch(`${apiUrl}/badges`);
			if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
			const result = await response.json();

			const userBadges = context.user.badges;

			// Create a Set of user badge IDs for fast lookup
			const ownedBadgeIds = new Set(userBadges.map(b => b.id));

			// Merge, annotate, and filter
			const combinedBadges = result
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
		<div className="gallery-container">
			<h2 className="badge-welcome">WELCOME, {user.name.toUpperCase()}</h2>
			<h3 className="badge-header">YOUR BADGES</h3>

			<Link to="/leaderboard" className="leaderboard-button common-button">Leaderboard &gt;</Link>
			<p className="badge-instruction">Click badge for details!</p>

			<div className="badge-grid">
				{badges.map(badge => (
					<BadgeCard key={badge.id} badge={badge} />
				))}
			</div>
		</div>
	);
};

export default BadgeGallery;