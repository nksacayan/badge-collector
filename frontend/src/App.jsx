import BadgeGallery from './pages/BadgeGallery';
import WelcomePage from './pages/WelcomePage';
import BadgeDetail from './pages/BadgeDetail';
import { BrowserRouter, Routes, Route } from "react-router";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<WelcomePage />} />
        <Route path="/badges" element={<BadgeGallery />} />
        <Route path="/badge/:id" element={<BadgeDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;