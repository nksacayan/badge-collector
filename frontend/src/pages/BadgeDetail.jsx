import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';

const mockBadgeData = {
	1: {
		name: 'KNOT BADGE',
		description:
			'Head to Station 2 and choose a knot to learn. When complete, bring your knot to Nick or Angela to claim your badge.',
		color: 'red',
	},
	// Add more badge entries here
};

const BadgeDetail = () => {
	const navigate = useNavigate();
	const { id } = useParams();
	const badge = mockBadgeData[id] || mockBadgeData[1];

	return (
		<div className="badge-detail-container">
			<button className="back-button" onClick={() => navigate(-1)}>Back</button>

			<div className={`badge-circle badge-${badge.color}`}>
				❤️
			</div>

			<h1 className="badge-name">{badge.name}</h1>
			<p className="badge-description">{badge.description}</p>
		</div>
	);
};

export default BadgeDetail;