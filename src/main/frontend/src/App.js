import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { Container } from 'react-bootstrap';
import Header from './components/Header';
import Home from './pages/Home';
import Login from './pages/login/Login';
import Books from './pages/books/Books';
import AddBook from './pages/books/AddBook';
import BookInfo from './pages/books/BookInfo';
import EditBook from './pages/books/EditBook';
import Posts from './pages/posts/Posts';
import AddPost from './pages/posts/AddPost';
import PostInfo from './pages/posts/PostInfo';
import EditPost from './pages/posts/EditPost';
import MyPosts from './pages/posts/MyPosts';
import Alert from './pages/posts/Alert';
import CommentForm from './pages/comments/CommentForm';
import EditComment from './pages/comments/EditComment';
import MyComments from './pages/comments/MyComments';
import Signup from './pages/members/Signup';
import Members from './pages/members/Members';
import MemberInfo from './pages/members/MemberInfo';
import EditMember from './pages/members/EditMember';

function App() {
  return (
    <div>
      <Router>
        <Header />
        <Container>
          <Routes>
            <Route path="/" exact={true} element={<Home />} />
            <Route path="/login" exact={true} element={<Login />} />
            <Route path="/logout" exact={true} element={<Home />} />
            {/* {도서 관련} */}
            <Route path="/admin/books" exact={true} element={<Books />} />
            <Route path="/admin/books/add" exact={true} element={<AddBook />} />
            <Route
              path="/admin/books/:bookId"
              exact={true}
              element={<BookInfo />}
            />
            <Route
              path="/admin/books/:bookId/edit"
              exact={true}
              element={<EditBook />}
            />
            {/* {게시글 관련} */}
            <Route path="/posts" exact={true} element={<Posts />} />
            <Route path="/posts/add" exact={true} element={<AddPost />} />
            <Route path="/posts/:postId" exact={true} element={<PostInfo />} />
            <Route
              path="/posts/:postId/edit"
              exact={true}
              element={<EditPost />}
            />
            <Route path="/posts/mine" exact={true} element={<MyPosts />} />
            <Route path="/alert/:postId" exact={true} element={<Alert />} />
            {/* {댓글 관련} */}
            <Route
              path="/posts/:postId/comments/add"
              exact={true}
              element={<CommentForm />}
            />
            <Route
              path="/posts/:postId/comments/:commentId"
              exact={true}
              element={<EditComment />}
            />
            <Route
              path="/posts/comments"
              exact={true}
              element={<MyComments />}
            />
            {/* {회원 관련} */}
            <Route path="/members/add" exact={true} element={<Signup />} />
            <Route path="/admin/members" exact={true} element={<Members />} />
            <Route
              path="/admin/members/:memberId"
              exact={true}
              element={<MemberInfo />}
            />
            <Route
              path="/members/:memberId"
              exact={true}
              element={<MemberInfo />}
            />
            <Route
              path="/admin/members/:memberId/edit"
              exact={true}
              element={<EditMember />}
            />
            <Route
              path="/members/:memberId/edit"
              exact={true}
              element={<EditMember />}
            />
          </Routes>
        </Container>
      </Router>
    </div>
  );
}

export default App;
