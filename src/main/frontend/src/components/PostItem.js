import React from 'react';
import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

function PostItem() {
  return (
    <Card style={{ width: '18rem' }}>
      <Card.Img
        variant="top"
        src="https://t4.ftcdn.net/jpg/00/53/45/31/360_F_53453175_hVgYVz0WmvOXPd9CNzaUcwcibiGao3CL.jpg"
      />
      <Card.Body>
        <Card.Title>제목 1</Card.Title>
        <Card.Text>
          Some quick example text to build on the card title and make up the
          bulk of the card's content.
        </Card.Text>
        <Link to="/posts/1" className="btn btn-primary" variant="primary">
          Go somewhere
        </Link>
      </Card.Body>
    </Card>
  );
}

export default PostItem;
