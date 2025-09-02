import { useNavigate, useParams } from 'react-router';
import './BadgeDetail.css';

const mockBadgeData = {
	1: {
		name: 'piss badge',
		title: 'how to piss',
		description: 'piss '.repeat(512), // placeholder for long text
	},
	// Add more badge entries here
};

const BadgeDetail = () => {
	const navigate = useNavigate();
	const { id } = useParams();
	const badge = mockBadgeData[id] || mockBadgeData[1]; // fallback for demo

	return (
		<div className="badge-detail-container">
			<button className="back-button" onClick={() => navigate(-1)}>←</button>
			<h1 className="badge-name">{badge.name}</h1>
			<div className="badge-circle" />
			<h2 className="badge-title">{badge.title}</h2>
			<p className="badge-description">{badge.description}</p>
		</div>
	);
};

export default BadgeDetail;