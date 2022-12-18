import React,{useState, useEffect, useCallback} from 'react';
import './App.css';
import axios from 'axios';
import { useDropzone } from 'react-dropzone';


const UserProfiles = () =>{
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res);
      setUserProfiles(res.data);
    });
  }
    useEffect(() => {
      fetchUserProfiles();
    },[]);
  
    return userProfiles.map((userProfile, index) => {
      return (
        <div key={index}>
          {userProfile.userId ? <img src = {'http://localhost:8080/api/v1/user-profile/${userProfile.userId}/image/download'}/>: null}
            {/* todo: profile image */}
            <br/>
            <br/>
            <h1>Here is dropzone</h1>
            <h1>{userProfile.username}</h1>
            <p>{userProfile.userId}</p>
            <Dropzone {...userProfile}/>
            <br/>
        </div>
      )
    } )
}

function Dropzone ({userId}){
  const onDrop = useCallback(acceptedFiles => {
   const file = acceptedFiles [0];
   
   console.log(file);
   
   const formData = new FormData ();
   
   formData.append("file", file)
    
   axios.post('http://localhost:8080/api/v1/user-profile/${userId}/image/upload',
   formData,
   {
    headers: {
      "Content-type":"multipart/form-data"
    }
   }
   ).then(() => {
     console.log("file uploaded successfully")
   }).catch(err => {
    console.log(err);
   })
  
  },[])


  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  return (
    <div {...getRootProps()}>
    <input {...getInputProps()}/>
    {
      isDragActive ? (
      <p>Drop the image here..</p>):(
      <p>Drag 'n' drop some profile image, or click to select profile image</p>
    )}
    </div>
  )

}

function App() {
  return (
    <div className="App">
      <h1>Drop your image here 📁 </h1>
      <br/>
      <br/>
      <br/>
      <hr/>
       <UserProfiles/>
    </div>
  );
}

export default App;
