import React, { useState } from 'react'
import RepeatIcon from '@mui/icons-material/Repeat';
import { Avatar, Button, Menu, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import VerifiedSharpIcon from '@mui/icons-material/VerifiedSharp';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FileUploadIcon from '@mui/icons-material/FileUpload';
import BarChartIcon from '@mui/icons-material/BarChart';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ReplyModal from './ReplyModal';


const TweetCard = () => {

    const navigate = useNavigate()

    const handleDeleteTweet = ()=>{
        console.log('Delete')
        handleClose()
    }

    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
      };
    
    const handleOpenReplyModel = ()=>{
        console.log('TweetCard button Pressed.')
    }
    
    const handleCreateRetweet = () => {
        console.log('Make a Retweet.')
    }
    const handleLikeTweet = () => {
        console.log('Tweet Liked')
    }
    // const handleDislikeTweet = () => {
    //     console.log('Tweet Disliked')
    // }

    // For TweetCard ReplyModal
    const [openReplyModal, setOpenReplyModal] = useState(false); //for Repl y Button
    const handleOpenReplyModal = () => setOpenReplyModal(true); //for Reply Button
    const handleCloseReplyModal = () => setOpenReplyModal(false); //for Reply Button

  return (
    <React.Fragment>
        
        {/* <div className='flex items-center font-semibold text-gray-700 py-2'>
            <RepeatIcon className='text-blue-500'/>
            <p>Yuo Retweet</p>
        </div> */}

        <div className='flex space-x-5'>
            <Avatar
                onClick = {() => navigate(`/profile/${5}`)}
                className='cursor-pointer'
                alt='Saumitra Bajpai' src='https://pbs.twimg.com/profile_images/1440003961163520000/1Q0ZK1ZJ_400x400.jpg'/>
            <div className='w-full'>
                <div className='flex justify-between items-center'>
                    <div className='flex cursor-pointer items-center space-x-2'>
                        <span className='font-semibold'>Saumitra Bajpai</span>
                        <span className='text-gray-600'>@SaumitraBajpai1009 . 2m</span>
                        <VerifiedSharpIcon className='ml-2 w- h-5 bg-cyan-400'/>
                    </div>
                    <div>
                        <Button
                            id="basic-button"
                            aria-controls={open ? 'basic-menu' : undefined}
                            aria-haspopup="true"
                            aria-expanded={open ? 'true' : undefined}
                            onClick={handleClick}
                        >
                            <MoreHorizIcon />
                        </Button>
                        <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={open}
                            onClose={handleClose}
                            MenuListProps={{
                                'aria-labelledby': 'basic-button',
                            }}
                        >
                            <MenuItem onClick={handleDeleteTweet}>Delete</MenuItem>
                            <MenuItem onClick={handleDeleteTweet}>Edit</MenuItem>
                        </Menu>
                    </div>
                        
                </div>
                        
                <div className='mt-2'>
                    <div onClick={()=>navigate(`/tweet/${3}`)} className='cursor-pointer' >
                            <p className='mb-2 p-0'>Twitter Clone - this is our full stack twitter project</p>
                            <img className='w-[28rem] border border-gray-400 p-5 rounded-md'
                            src='https://s3.amazonaws.com/awsmp-logos/cloudinary.png' alt='Image for tweet'>
                            </img>
                    </div>
                        
                    <div className='py-5 flex flex-wrap justify-between items-center'>
                        <div className='flex space-x-3 items-center text-gray-600'>
                            <ChatBubbleOutlineIcon className='cursor-pointer' onClick={handleOpenReplyModal}/>
                            <p>43</p>
                        </div>
                        
                        <div className={`${true?'text-pink-600':'text-gray-600'} flex space-x-3 items-center`}>
                            <RepeatIcon className='cursor-pointer' onClick={handleCreateRetweet}/>
                            <p>54</p>
                        </div>
                        
                        <div className={`${true?'text-pink-600':'text-gray-600'} flex space-x-3 items-center`}>
                            {true? <FavoriteIcon className='cursor-pointer' onClick={handleLikeTweet}/> : 
                            <FavoriteBorderIcon className='cursor-pointer' onClick={handleLikeTweet}/>}
                            <p>54</p>
                        </div>
                        
                        <div className='flex space-x-3 items-center text-gray-600'>
                            <BarChartIcon className='cursor-pointer' onClick={handleOpenReplyModel}/>
                            <p>430</p>
                        </div>
                        
                        <div className='flex space-x-3 items-center text-gray-600'>
                            <FileUploadIcon className='cursor-pointer' onClick={handleOpenReplyModel}/>
                        </div>
                    </div>
                        
                </div>
                        
            </div>
        </div>
        <section>
            <ReplyModal open={openReplyModal} handleClose={handleCloseReplyModal}/>
        </section>
    </React.Fragment>
  )
}

export default TweetCard