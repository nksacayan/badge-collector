import { Link } from 'react-router';
import BadgeCard from '../components/BadgeCard';
import './BadgeGallery.css';

const mockBadges = [
	{ id: 1, name: 'Firestarter', earned: true, color: 'red' },
	{ id: 2, name: 'Trailblazer', earned: true, color: 'green' },
	{ id: 3, name: 'Tent Titan', earned: false, color: 'yellow' },
	{ id: 4, name: 'Compass Commander', earned: false, color: 'blue' },
	{ id: 5, name: 'S’more Specialist', earned: false, color: 'brown' },
	{ id: 6, name: 'Scout Supreme', earned: true, color: 'pink' },
];

const BadgeGallery = () => {
	return (
		<div className="gallery-container">
			<h2 className="badge-welcome">WELCOME, Nick</h2>
			<h3 className="badge-subtitle">YOUR BADGES</h3>

			<Link to="/leaderboard" className="leaderboard-button">Leaderboard &gt;</Link>
			<p className="badge-instruction">Click badge for details!</p>

			<div className="badge-grid">
				{mockBadges.map(badge => (
					<BadgeCard key={badge.id} badge={badge} />
				))}
			</div>
		</div>
	);
};

export default BadgeGallery;