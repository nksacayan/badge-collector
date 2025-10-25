import './BadgeCard.css';
import { useNavigate } from 'react-router';
import badgePlaceholder from '../assets/Badge_Placeholder.png';

const BadgeCard = ({ badge }) => {
	const navigate = useNavigate();
	const handleClick = () => {
		navigate(`/badge/${badge.id}`);
	};

	return (
		// This will be a clickable icon down the road
		<div
			className={`badge-card ${badge.earned ? 'earned' : 'locked'}`}
			onClick={handleClick}
		>
			<img id="badge-placeholder-icon" src={badgePlaceholder} alt="Badge Icon" />

		</div>
	);
};

export default BadgeCard;