import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import { useFormik } from 'formik';
import { Avatar, IconButton, TextField } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { useState } from 'react';
import './ProfileModal.css'

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 600,
    bgcolor: 'background.paper',
    border: 'none',
    boxShadow: 24,
    p: 4,
    outline: 'none',
    borderRadius: 4
  };

const ProfileModal = ({open, handleClose}) => {

    const[uploading, setUploading] = useState(false);

    const handleSubmit = (values)=>{
      console.log('Submitted. Profile Modal.', values)
    }

    const handleImageChange = (event)=>{
      setUploading(true)
      const {name} = event.target
      const {file} = event.target.files[0];
      formik.setFieldValue(name,file)
      setUploading(false)
    }

    const formik = useFormik ({
        initialValues: {
          fullName: '',
          website: '',
          location: '',
          bio: '',
          backgroundImage: '',
          image: '',
        },
        onSubmit: handleSubmit
      })

    return (
      // Modal
      <div>
        {/* <Button onClick={handleOpen}>Open modal</Button> */}
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          {/* Top nav of modal */}
          <Box sx={style}>
            <form onSubmit={formik.handleSubmit}>
              <div className='flex items-center justify-between'>
                <div className='flex items-center space-x-3'>
                  <IconButton onClick={handleClose} aria-label='delete'>
                    <CloseIcon />
                  </IconButton>
                  <p className='text-sm'>Edit Profile</p>
                </div>
                <Button type='submit'>Save</Button>
              </div>

              {/* Profile Pic and cover pic */}
              <div className='hideScrollBar overflow-y-scroll overflow-x-hidden h-[80vh]'>
                <React.Fragment>
                  <div className='w-full'>
                    <div className='relative'>
                      <img src="https://cdn.pixabay.com/photo/2022/03/27/12/46/chongqing-7094955_960_720.jpg" alt="profile" 
                        className='w-full h-[12rem] object-cover object-center'/>
                      {/*Image Uploading Input  */}
                      <input type='file' className='absolute top-0 left-0 w-full h-full opacity-50 cursor-pointer'
                        onChange={handleImageChange}
                        name='backgroundImage'/>
                    </div>
                  </div> 

                  {/* Avatar */}
                  <div className='w-full transform -translate-y-20 ml-4 h-[6rem]'>
                    <div className='relative'>
                      <Avatar sx={{width:"10rem", height:"10rem", border:"4px solid white"}}
                      alt='Saumitra Bajpai' src='https://preview.redd.it/funny-chill-guy-meme-v0-2o5zksci243e1.jpeg?width=640&crop=smart&auto=webp&s=65c56d356b1344b9032302e31eae6371022206df'
                      />
                      <input className='absolute top-0 left-0 w-[10rem] h-full opacity-0 cursor-pointer' type='file'
                      onChange={handleImageChange} name="image" /> 
                    </div>
                  </div>

                </React.Fragment>
                {/*FullName, Bio, Work etc form  */}
                <div className='space-y-3'>
                  <TextField
                    fullWidth
                    id='fullName'
                    name='fullName'
                    label='Full Name'
                    value={formik.values.fullName}
                    onChange={formik.handleChange}
                    error={formik.touched.fullName && Boolean(formik.errors.fullName)}
                    helperText={formik.touched.fullName && formik.errors.fullName}/>

                  <TextField
                    fullWidth
                    multiline
                    rows={4}
                    id='bio'
                    name='bio'
                    label='Bio'
                    value={formik.values.bio}
                    onChange={formik.handleChange}
                    error={formik.touched.bio && Boolean(formik.errors.bio)}
                    helperText={formik.touched.bio && formik.errors.bio}/>

                  <TextField
                    fullWidth
                    id='website'
                    name='website'
                    label='Website'
                    value={formik.values.website}
                    onChange={formik.handleChange}
                    error={formik.touched.website && Boolean(formik.errors.website)}
                    helperText={formik.touched.website && formik.errors.website}/>

                  <TextField
                    fullWidth
                    id='location'
                    name='location'
                    label='Location'
                    value={formik.values.location}
                    onChange={formik.handleChange}
                    error={formik.touched.location && Boolean(formik.errors.location)}
                    helperText={formik.touched.location && formik.errors.location}/>

                  <div className='my-3'>
                    <p className='text-lg'>Birth Date . Edit</p>
                    <p className='text-2xl'> September 10, 1998</p>
                  </div>
                  <p className='py-3 text-lg'>Edit Professional Profile</p>
                </div>

              </div>
            </form>
          </Box>
        </Modal>
      </div>
    )
}

export default ProfileModal