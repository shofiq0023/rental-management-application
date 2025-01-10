
import { Layout } from 'antd';
import React, { useState } from 'react'
import SiderComponent from './SiderComponent';
import HeaderComponent from './HeaderComponent';
import MainContent from './MainContent';


const DashBoardContent = ({ children }) => {


  const [collapsed, setCollapsed] = useState(false);
  const toggleCollapsed = () => setCollapsed(!collapsed);

  return (
    <Layout>
      {/* Sider Component */}
      <SiderComponent collapsed={collapsed} />

      <Layout>
        {/* Header Component */}
        <HeaderComponent collapsed={collapsed} toggleCollapsed={toggleCollapsed} />

        {/* Main Content */}
        <MainContent>{children}</MainContent>
      </Layout>
    </Layout>
  );
};

export default DashBoardContent;