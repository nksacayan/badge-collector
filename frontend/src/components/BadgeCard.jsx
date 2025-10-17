import './BadgeCard.css';
import { useNavigate } from 'react-router';

const BadgeCard = ({ badge }) => {
	const navigate = useNavigate();
	const handleClick = () => {
		navigate(`/badge/${badge.id}`);
	};

	return (
		<div
			className={`badge-card badge-${badge.color} ${badge.earned ? 'earned' : 'locked'}`}
			onClick={handleClick}
		>
			<div className="badge-circle">
				❤️
			</div>
			<p>{badge.name}</p>
		</div>
	);
};

export default BadgeCard;