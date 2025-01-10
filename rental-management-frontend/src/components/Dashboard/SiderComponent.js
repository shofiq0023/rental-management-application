import React from 'react';
import { Layout, Menu } from 'antd';
import { UserOutlined, DashboardOutlined, AppstoreAddOutlined } from '@ant-design/icons';
import { useLocation, useNavigate } from 'react-router-dom';

const { Sider } = Layout;

const SiderComponent = ({ collapsed }) => {
  const location = useLocation();
  const navigate = useNavigate();

  // Map menu items to their respective routes
  const menuItems = [
    {
      key: '/home',
      icon: <DashboardOutlined />,
      label: 'Dashboard',
    },
    {
      key: '/create',
      icon: <AppstoreAddOutlined />,
      label: 'Create',
    },
    {
      key: '/uploads',
      icon: <AppstoreAddOutlined />,
      label: 'File Uploads',
    },
  ];

  return (
    <Sider trigger={null} collapsible collapsed={collapsed} className="h-screen">
      <div className="demo-logo-vertical mt-20" />
      <Menu
        theme="dark"
        mode="inline"
        selectedKeys={[location.pathname]} // Dynamically select the menu item based on the current route
        onClick={({ key }) => navigate(key)} // Navigate to the clicked menu item
        items={menuItems}
      />
    </Sider>
  );
};

export default SiderComponent;
