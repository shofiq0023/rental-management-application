import React from 'react';
import image1 from "../../assets/property.jpg";
import { Button, Form, Input, Select } from 'antd';
import { Link } from 'react-router-dom';

const Register = () => {


    const onFinish=()=>{


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
        <h2 className="text-2xl font-bold text-center mb-6">Register</h2>
        <Form
                            layout="vertical" onFinish={onFinish}>
                                <Form.Item name='name' label='User Name' rules={[{ required: true, message: 'Please enter your name!' }]} >
                                <Input  />
                            </Form.Item>
                            <Form.Item name='email' label='Email' rules={[{ required: true, message: 'Please enter your Email!' }]} >
                                <Input type='email'  />
                            </Form.Item>
                            <Form.Item name='password' label='Password' rules={[{ required: true, message: 'Please enter your Password!' }]} >
                                <Input />
                            </Form.Item>
                            <Form.Item name='user' label='User Type' rules={[{ required: true, message: 'Please Select User Type' }]} >
                            <Select
              defaultValue={
                { value: 'renter', label: 'Renter' }
              }
             
              options={[
                { value: 'owner', label: 'Owner' },
                { value: 'renter', label: 'Renter' },
              ]}
            />
                            </Form.Item>
                            <div>
                                <Button htmlType='submit' type='primary' className='w-full bg-primary text-white mb-4 mt-1'>Save</Button>
                            </div>
                        </Form>

        <Link className="underline hover:text-blue-800" to="/login">
          If you have  account then Login ?
        </Link>
      </div>
    </div>
  );
};

export default Register;
