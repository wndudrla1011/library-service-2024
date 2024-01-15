import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import PostItem from '../components/PostItem';

function Home() {
  return (
    <div>
      <PostItem />
    </div>
  );
}

export default Home;
