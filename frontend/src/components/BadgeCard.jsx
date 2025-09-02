import './BadgeCard.css';

const BadgeCard = ({ badge }) => {
	const handleClick = () => {
		alert(`Badge: ${badge.name}`);
		// Later: navigate to /badge/:id or open modal
	};

	return (
		<div
			className={`badge-card ${badge.earned ? 'earned' : 'locked'}`}
			onClick={handleClick}
		>
			<div className="badge-circle">
				{badge.earned ? '🏅' : '🔒'}
			</div>
			<p>{badge.name}</p>
		</div>
	);
};

export default BadgeCard;