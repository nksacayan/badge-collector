import { Link } from 'react-router';
import BadgeCard from '../components/BadgeCard';
import './BadgeGallery.css';
import { useContext } from "react";
import { UserContext } from "../components/UserContext";


const BadgeGallery = () => {
	const context = useContext(UserContext);
	const { user } = context;

	return (
		<div className="gallery-container">
			<h2 className="badge-welcome">WELCOME, {user.name.toUpperCase()}</h2>
			<h3 className="badge-header">YOUR BADGES</h3>

			<Link to="/leaderboard" className="leaderboard-button common-button">Leaderboard &gt;</Link>
			<p className="badge-instruction">Click badge for details!</p>

			<div className="badge-grid">
				{user?.badges.map(badge => (
					<BadgeCard key={badge.id} badge={badge} />
				))}
			</div>
		</div>
	);
};

export default BadgeGallery;