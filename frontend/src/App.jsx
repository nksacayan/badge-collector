import BadgeGallery from './pages/BadgeGallery';
import WelcomePage from './pages/WelcomePage';
import BadgeDetail from './pages/BadgeDetail';
import { BrowserRouter, Routes, Route } from "react-router";
import { UserProvider } from './components/UserProvider';

function App() {
  return (
    <BrowserRouter>
      <UserProvider>
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/badges" element={<BadgeGallery />} />
          <Route path="/badge/:badgeId" element={<BadgeDetail />} />
        </Routes>
      </UserProvider>
    </BrowserRouter>
  );
}

export default App;