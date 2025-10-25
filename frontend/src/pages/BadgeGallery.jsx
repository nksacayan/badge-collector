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
	{ id: 7, name: 'Scout Supreme', earned: true },
	{ id: 8, name: 'Scout Supreme', earned: true },
	{ id: 9, name: 'Scout Supreme', earned: true },
	{ id: 10, name: 'Scout Supreme', earned: true },
	{ id: 11, name: 'Scout Supreme', earned: true },
	{ id: 12, name: 'Scout Supreme', earned: true },
	{ id: 13, name: 'Scout Supreme', earned: true },
	{ id: 14, name: 'Scout Supreme', earned: true },
	{ id: 15, name: 'Scout Supreme', earned: true },
	{ id: 16, name: 'Scout Supreme', earned: true },
	{ id: 17, name: 'Scout Supreme', earned: true },
	{ id: 18, name: 'Scout Supreme', earned: true },
	{ id: 19, name: 'Scout Supreme', earned: true },
	{ id: 20, name: 'Scout Supreme', earned: true },
	{ id: 21, name: 'Scout Supreme', earned: true },
	{ id: 22, name: 'Scout Supreme', earned: true },
	{ id: 23, name: 'Scout Supreme', earned: true },
	{ id: 24, name: 'Scout Supreme', earned: true },
	{ id: 25, name: 'Scout Supreme', earned: true },
	{ id: 26, name: 'Scout Supreme', earned: true },
	{ id: 27, name: 'Scout Supreme', earned: true },
	{ id: 28, name: 'Scout Supreme', earned: true },
	{ id: 29, name: 'Scout Supreme', earned: true },
	{ id: 30, name: 'Scout Supreme', earned: true },
	{ id: 31, name: 'Scout Supreme', earned: true },
	{ id: 32, name: 'Scout Supreme', earned: true },
	{ id: 33, name: 'Scout Supreme', earned: true },
	{ id: 34, name: 'Scout Supreme', earned: true },
	{ id: 35, name: 'Scout Supreme', earned: true },
	{ id: 36, name: 'Scout Supreme', earned: true },
	{ id: 37, name: 'Scout Supreme', earned: true },
	{ id: 38, name: 'Scout Supreme', earned: true },
	{ id: 39, name: 'Scout Supreme', earned: true },
];

const BadgeGallery = () => {
	return (
		<div className="gallery-container">
			<h2 className="badge-welcome">WELCOME, Nick</h2>
			<h3 className="badge-header">YOUR BADGES</h3>

			<Link to="/leaderboard" className="leaderboard-button common-button">Leaderboard &gt;</Link>
			<p className="badge-instruction">Click badge for details!</p>

			<div className="badge-grid">
				{mockBadges.map(badge => (
					<BadgeCard key={badge.id} badge={badge} isEarned={badge.earned} />
				))}
			</div>
		</div>
	);
};

export default BadgeGallery;