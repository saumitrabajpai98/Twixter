import React from 'react'
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import { useNavigate } from 'react-router-dom';
import TweetCard from '../HomeSection/TweetCard';
import { Divider } from '@mui/material';


const TweetDetails = () => {

    const navigate = useNavigate()
    const handleBack = ()=>navigate(-1)

  return (
    <React.Fragment>

        {/* Arrow Button with Header(PageInfo: "Tweet") */}
        <section className='bg-white z-50 flex items-center sticky top-0 bg-opacity-90'>
          <KeyboardBackspaceIcon className='cursor-pointer' onClick={handleBack}/>
        <h1 className='py-5 font-bold text-xl ml-5 opacity-90'>Tweet</h1>
      </section>

      {/* Display Clicked TweetCard w.r.t its TweetID */}
      <section>
        <TweetCard/>
        <Divider sx={{margin:"2rem 0rem", bgcolor:"blue"}}/>
      </section>

      {/* Display Replies to the Tweet */}
      <section>
        {[1,1,1].map((item)=> <TweetCard/>)}
      </section>

    </React.Fragment>
  )
}

export default TweetDetails