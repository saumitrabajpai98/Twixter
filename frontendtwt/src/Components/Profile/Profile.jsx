import React, { useState } from 'react'
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import { useNavigate } from 'react-router-dom';
import { Avatar, Box, Button, Tab, Tabs } from '@mui/material';
import {TabContext, TabList, TabPanel} from '@mui/lab';
import VerifiedSharpIcon from '@mui/icons-material/VerifiedSharp';
import BusinessCenterIcon from '@mui/icons-material/BusinessCenter';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import TweetCard from '../HomeSection/TweetCard';
import ProfileModal from './ProfileModal';

const Profile = () => {

    const [openProfileModel, setOpenProfileModel] = React.useState(false); //for EditProfile Button
    const handleOpenProfileModel = () => setOpenProfileModel(true); //for EditProfile Button
    const handleClose = () => setOpenProfileModel(false); //for EditProfile Button

    const navigate = useNavigate()
    const handleBack = ()=>navigate(-1)

    const handleFollowUser = ()=>{
      console.log('Follow User')
    }

    const [tabValue, setTabValue] = useState('1');

    const handleTabChange = (event, newValue) =>{
      setTabValue(newValue)

      if (newValue === 4){
        console.log('Like Tweet')  
      }
      else if (newValue === 1){
        console.log('User Tweets')
      }
    }

  return (
    // Name and back arrow
    <div>
      <section className='bg-white z-50 flex items-center sticky top-0 bg-opacity-90'>
          <KeyboardBackspaceIcon className='cursor-pointer' onClick={handleBack}/>
        <h1 className='py-5 font-bold text-xl ml-5 opacity-90'>Saumitra Bajpai</h1>
      </section>

      {/* Cover Image */}
      <section>
        <img src="https://cdn.pixabay.com/photo/2022/03/27/12/46/chongqing-7094955_960_720.jpg" alt="profile" 
              className='w-[100%] h-[15rem] object-cover'/>
      </section>

      {/* Profile Pic & Follow/Unfollow Button */}
      <section className='pl-6'>
        <div className='flex justify-between items-start mt-5 h-[5rem]'>
          <Avatar className='transform -translate-y-20' //to move the avatar up a bit
          src="https://preview.redd.it/funny-chill-guy-meme-v0-2o5zksci243e1.jpeg?width=640&crop=smart&auto=webp&s=65c56d356b1344b9032302e31eae6371022206df" alt="Saumitra Bajpai"
          sx={{width:"10rem", height:"10rem", border:"4px solid white"}}/>
          
          {
            // when u will integrate the backend, you change the T/F accordingly
            true? (<Button variant='contained' sx={{borderRadius:'20px'}}
            onClick={handleOpenProfileModel}>
              Edit Profile
            </Button>):
            (<Button variant='contained' sx={{borderRadius:'20px'}}
                    onClick={handleFollowUser}>
              {false? "Follow":"Unfollow"}
            </Button>) 
          }
        </div>
        
        {/* Name & Handle */}
        <div>
          <div className='flex items-center'>
            <h1 className='font-bold text-lg'>Saumitra Bajpai</h1>
            {true && (<VerifiedSharpIcon className='ml-2 w- h-5 bg-cyan-400'/>)}
          </div>
          <h1 className='text-gray-500 ml-2'>@SaumitraBajpai1009</h1>
        </div>

        {/* Bio */}
        <div className='mt-2 space-y-3'>
          <p>Hello World! I am Saumitra Bajpai. Trying to communicate with person who speaks only in Binary.~_~</p>
          
          <div className='flex space-x-5 py-1'>
            <div className='flex items-center text-gray-500'>
              <BusinessCenterIcon/>
              <p className='ml-2'>Education</p>
            </div>

            <div className='flex items-center text-gray-500'>
              <LocationOnIcon/>
              <p className='ml-2'>Indian</p>
            </div>

            <div className='flex items-center text-gray-500'>
              <CalendarMonthIcon/>
              <p className='ml-2'>Joined September 2022</p>
            </div>
          </div>

          <div className='flex items-center space-x-5'>
            <div className='flex items-center space-x-2 font-semibold'>
              <span>540</span>
              <span className='text-gray-500'>Following</span>
            </div>
          
            <div className='flex items-center space-x-2 font-semibold'>
              <span>590</span>
              <span className='text-gray-500'>Followers</span>
            </div>
          </div>
                                    
        </div>

      </section>

      {/* Tabs like Tweet, Media, Replies, Likes etc.*/}
      <section className='py-5'>
        <Box sx={{ width: '100%', typography: 'body1' }}>
        <TabContext value={tabValue}>
          <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
            <TabList onChange={handleTabChange} aria-label="lab API tabs example">
              <Tab label="Tweets" value="1" />
              <Tab label="Replies" value="2" />
              <Tab label="Media" value="3" />
              <Tab label="Likes" value="4" />
            </TabList>
          </Box>
          <TabPanel value="1">
            {[1,1,1,1,1].map((item) => <TweetCard/>)}
          </TabPanel>
          <TabPanel value="2">User's Replies</TabPanel>
          <TabPanel value="3">Media</TabPanel>
          <TabPanel value="4">Likes</TabPanel>
        </TabContext>
        </Box>
      </section>

      <section>
        <ProfileModal handleClose={handleClose} open={openProfileModel} />
      </section>
    </div>
  )
}

export default Profile