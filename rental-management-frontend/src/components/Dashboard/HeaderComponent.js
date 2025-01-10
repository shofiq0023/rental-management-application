import React from 'react';
import { Button } from 'antd';
import { MenuFoldOutlined, MenuUnfoldOutlined } from '@ant-design/icons';

const HeaderComponent = ({ collapsed, toggleCollapsed }) => {
  return (
    <header
      style={{
        padding: 0,
        background: '#f0f2f5',
      }}
    >
      <Button
        type="text"
        icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
        onClick={toggleCollapsed}
        style={{
          fontSize: '16px',
          width: 64,
          height: 64,
        }}
      />
    </header>
  );
};

export default HeaderComponent;
