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
	{ id: 7, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 8, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 9, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 10, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 11, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 12, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 13, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 14, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 15, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 16, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 17, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 18, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 19, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 20, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 21, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 22, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 23, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 24, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 25, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 26, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 27, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 28, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 29, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 30, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 31, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 32, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 33, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 34, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 35, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 36, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 37, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 38, name: 'Scout Supreme', earned: true, color: 'pink' },
	{ id: 39, name: 'Scout Supreme', earned: true, color: 'pink' },
];

const BadgeGallery = () => {
	return (
		<div className="gallery-container">
			<h2 className="badge-welcome">WELCOME, Nick</h2>
			<h3 className="badge-header">YOUR BADGES</h3>

			{/* <Link to="/leaderboard" className="leaderboard-button">Leaderboard &gt;</Link> */}
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