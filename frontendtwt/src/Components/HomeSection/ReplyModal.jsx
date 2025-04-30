import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { useNavigate } from 'react-router-dom';
import { Avatar, Button, Menu, MenuItem } from '@mui/material';
import VerifiedSharpIcon from '@mui/icons-material/VerifiedSharp';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import ImageIcon from '@mui/icons-material/Image';
import FmdGoodIcon from '@mui/icons-material/FmdGood';
import TagFacesIcon from '@mui/icons-material/TagFaces';
import { useFormik } from 'formik';
import { useState } from 'react';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600,
  bgcolor: 'background.paper',
  border: 'none',
  outline: 'none',
  borderRadius: 4,
  boxShadow: 24,
  p: 4,
};

export default function ReplyModal({handleClose, open}) {

//   const [open, setOpen] = useState(false);
//   const handleOpen = () => setOpen(true);
//   const handleClose = () => setOpen(false);
  const navigate = useNavigate();  

  const [uploadingImage, setUploadingImage] = useState(false);
  const [selectImage, setSelectedImage] = useState('');
  
//   const [anchorEl, setAnchorEl] = React.useState(null);

  const handleSubmit = (values)=>{
    console.log("Handle Submit.",values)
  }

  const formik = useFormik({
    initialValues:{content:"", image:"", tweetID:5},
    onSubmit:handleSubmit
  })

  const handleSelectImage = (event) => {
    setUploadingImage(true)
    const imgURL = event.target.files[0]
    formik.setFieldValue('image', imgURL)
    setSelectedImage(imgURL)
    setUploadingImage(false)
  };

//   const handleClick = (event) => {
//       setAnchorEl(event.currentTarget);
//   };

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
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
                    </div>
                            
                    <div className='mt-2'>
                        <div onClick={()=>navigate(`/tweet/${3}`)} className='cursor-pointer' >
                                <p className='mb-2 p-0'>Twitter Clone - this is our full stack twitter project</p>
                        </div>     
                    </div>
                            
                </div>
            
            </div>
            
            {/* This is What's Happening Section of Tweet Reply Modal */}
            <section className={`py-10`}>
            <div className='flex space-x-5'>
                <Avatar alt='Saumitra Bajpai'
                        src='https://pbs.twimg.com/profile_images/1440003961163520000/1Q0ZK1ZJ_400x400.jpg' />
                <div className='w-full'>
                    <form onSubmit={formik.handleSubmit}>
                        <div>
                            <input type='text' name='content' placeholder="What is happening?" 
                            className={`border-none outline-none text-xl bg-transparent`}
                            {...formik.getFieldProps('content')}/> 
                            {formik.errors.content && formik.touched.content && (
                                <span className='text-red-500'>{formik.errors.content}</span>)}
                        </div>
                        
                        {/* Process To upload and Handle Image */}

                        <div className='flex justify-between items-center mt-5'>
                            <div className='flex space-x-5 items-center'>
                                <label className='flex items-center space-x-2 rounded-md cursor-pointer'>
                                    <ImageIcon className='text-[#1d9bf0]'/>
                                    <input type='file' name='imageFile' className='hidden' onChange={handleSelectImage} />
                                </label>
                                <FmdGoodIcon className='text-[#1d9bf0]'/>
                                <TagFacesIcon className='text-[#1d9bf0]'/>
                            </div>

                            <div>
                                <Button
                                    sx={{ width:'100%', borderRadius:'20px', paddingY:'8px', paddingX:'20px', bgcolor: '#1e88e5' }}
                                    variant='contained'
                                    type='submit'>
                                    Tweet
                                </Button>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
            </section>
        </Box>
      </Modal>
      {/* </Button> */}
    </div>
  );
}