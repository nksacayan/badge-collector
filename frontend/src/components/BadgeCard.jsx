import './BadgeCard.css';
import { useNavigate } from 'react-router';

const BadgeCard = ({ badge }) => {
	const navigate = useNavigate();
	const handleClick = () => {
		navigate(`/badge/${badge.id}`);
	};

	return (
		// This will be a clickable icon down the road
		<div
			className={`badge-card badge-${badge.color} ${badge.earned ? 'earned' : 'locked'}`}
			onClick={handleClick}
		>
			<p>❤️</p>
		</div>
	);
};

export default BadgeCard;