import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { Container } from 'react-bootstrap';
import Header from './components/Header';
import Home from './pages/Home';

function App() {
  return (
    <div>
      <Router>
        <Header />
        <Container>
          <Routes>
            <Route path="/" exact={true} element={<Home />} />
            <Route path="/login" exact={true} element={''} />
            <Route path="/logout" exact={true} element={''} />
            <Route path="/admin" exact={true} element={''} />
            {/* {도서 관련} */}
            <Route path="/admin/books" exact={true} element={''} />
            <Route path="/admin/books/add" exact={true} element={''} />
            <Route path="/admin/books/:bookId" exact={true} element={''} />
            <Route path="/admin/books/:bookId/edit" exact={true} element={''} />
            {/* {게시글 관련} */}
            <Route path="/posts" exact={true} element={''} />
            <Route path="/posts/add" exact={true} element={''} />
            <Route path="/posts/:postId" exact={true} element={''} />
            <Route path="/posts/:postId/edit" exact={true} element={''} />
            <Route path="/posts/mine" exact={true} element={''} />
            <Route path="/alert/:postId" exact={true} element={''} />
            {/* {댓글 관련} */}
            <Route
              path="/posts/:postId/comments/add"
              exact={true}
              element={''}
            />
            <Route
              path="/posts/:postId/comments/:commentId"
              exact={true}
              element={''}
            />
            <Route path="/posts/comments" exact={true} element={''} />
            {/* {회원 관련} */}
            <Route path="/members/add" exact={true} element={''} />
            <Route path="/admin/members" exact={true} element={''} />
            <Route path="/admin/members/:memberId" exact={true} element={''} />
            <Route
              path="/admin/members/:memberId/edit"
              exact={true}
              element={''}
            />
            <Route path="/download/:fileId" exact={true} element={''} />
          </Routes>
        </Container>
      </Router>
    </div>
  );
}

export default App;
