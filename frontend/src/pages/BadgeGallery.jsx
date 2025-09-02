import { Link } from 'react-router';
import BadgeCard from '../components/BadgeCard';
import './BadgeGallery.css';

const mockBadges = [
	{ id: 1, name: 'Firestarter', earned: true },
	{ id: 2, name: 'Trailblazer', earned: true },
	{ id: 3, name: 'Tent Titan', earned: false },
	{ id: 4, name: 'Compass Commander', earned: false },
	{ id: 5, name: 'S’more Specialist', earned: false },
	{ id: 6, name: 'Scout Supreme', earned: true },
];

const BadgeGallery = () => {
	return (
		<div className="gallery-container">
			<h1>Your Badges</h1>
			{/* <Link to="/leaderboard" className="leaderboard-link">leaderboard</Link> */}
			<p><em>click the badge for details</em></p>

			<div className="badge-grid">
				{mockBadges.map(badge => (
					<BadgeCard key={badge.id} badge={badge} />
				))}
			</div>
		</div>
	);
};

export default BadgeGallery;