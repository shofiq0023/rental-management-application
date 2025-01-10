
import React, { useState } from 'react'
import {
    MenuFoldOutlined,
    MenuUnfoldOutlined,
    UploadOutlined,
    UserOutlined,
    VideoCameraOutlined,
  } from '@ant-design/icons';


import { Button, Layout, Menu,theme } from 'antd';

const { Header, Sider, Content } = Layout;

const DashBoardContent = ({children}) => {
    const [collapsed, setCollapsed] = useState(false);
    const {
      token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
  
  return (
    <Layout>
      <Sider trigger={null} collapsible collapsed={collapsed} className='h-screen'>
        <div className="demo-logo-vertical mt-20" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={['1']}
          items={[
            {
              key: '1',
              icon: <UserOutlined />,
              label: 'User Management',
            },
            {
              key: '2',
              icon: <VideoCameraOutlined />,
              label: 'Video Management',
            },
            {
              key: '3',
              icon: <UploadOutlined />,
              label: 'File Uploads',
            },
          ]}
        />
      </Sider>

      <Layout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <Button
            type="text"
            icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            onClick={() => setCollapsed(!collapsed)}
            style={{
              fontSize: '16px',
              width: 64,
              height: 64,
            }}
          />
        </Header>
        <Content
          style={{
            margin: '24px 16px',
            padding: 24,
            minHeight: 280,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          {children}
        </Content>
      </Layout>
    </Layout>
  )
}

export default DashBoardContent;