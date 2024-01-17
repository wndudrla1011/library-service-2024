import React from 'react';
import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

function PostItem({ id, title, content, author }) {
  return (
    <Card style={{ width: '18rem' }}>
      <Card.Img
        variant="top"
        src="https://t4.ftcdn.net/jpg/00/53/45/31/360_F_53453175_hVgYVz0WmvOXPd9CNzaUcwcibiGao3CL.jpg"
      />
      <Card.Body>
        <Card.Title>{title}</Card.Title>
        <Card.Text>{content}</Card.Text>
        <Link to={'/posts/' + id} className="btn btn-primary">
          게시물 보기
        </Link>
        <Card.Subtitle>{author}</Card.Subtitle>
      </Card.Body>
    </Card>
  );
}

export default PostItem;
