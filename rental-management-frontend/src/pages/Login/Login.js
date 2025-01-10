import React from 'react';
import image1 from "../../assets/property.jpg";
import { Button, Form, Input } from 'antd';
import { Link, useNavigate } from 'react-router-dom';

const Login = () => {

  const navigate = useNavigate();

  const onFinished=(values)=>{

    console.log('Form Values:', values);
  
    navigate('/home');
  }
  return (
    <div 
      className="bg-cover bg-center bg-no-repeat h-screen flex items-center justify-center" 
      style={{ backgroundImage: `url(${image1})` }}
    >
      {/* Overlay for blur effect */}
      <div className="absolute inset-0 bg-black/50 backdrop-blur-md"></div>

      {/* Content */}
      <div className="relative z-10 bg-white p-8 rounded-lg shadow-lg max-w-md w-full">
        <h2 className="text-2xl font-bold text-center mb-6">Login</h2>
        <Form className="space-y-2" layout="vertical" onFinish={onFinished}>
          {/* Email Input */}
          <Form.Item label="Email">
        <Input placeholder="Enter Your Email"  type='email' required={true}/>
      </Form.Item>

          {/* Password Input */}
          <Form.Item label="Password" className='mb-3'>
        <Input placeholder="Enter Your Password"  type='password' required={true}/>
      </Form.Item> 

          {/* Submit Button */}
          <Form.Item>
        <Button htmlType='submit' type='primary'  className='w-full bg-primary text-white mb-4'>Submit</Button>
      </Form.Item>
        </Form>

        <Link className=" underline hover:text-blue-800" to="/register">
          If you have no account then Register ?
        </Link>
      </div>
    </div>
  );
};

export default Login;
