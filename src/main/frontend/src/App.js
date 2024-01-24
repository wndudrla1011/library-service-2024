import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Join from './pages/Join';
import LoginContextProvider from './contexts/LoginContextProvider';

function App() {
  return (
    <div>
      <Router>
        <LoginContextProvider>
          <Routes>
            <Route path="/" exact={true} element={<Home />} />
            {/* {도서 관련} */}
            {/* {게시글 관련} */}
            {/* {댓글 관련} */}
            {/* {회원 관련} */}
            <Route path="/login" exact={true} element={<Login />} />
            <Route path="/logout" exact={true} element={<Home />} />
            <Route path="/members/add" exact={true} element={<Join />} />
          </Routes>
        </LoginContextProvider>
      </Router>
    </div>
  );
}

export default App;
