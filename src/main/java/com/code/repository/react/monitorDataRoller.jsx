import React from "react";
import {Grid} from '@alife/next';
import CommonUtil from 'commonPath/common.js';
import API from 'commonPath/api';
import 'commonPath/common.scss';
import "./page.scss";

const {Row,Col} = Grid;

// 异常处理实时信息
class ErrorOrderMonitor extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            orders: ["start..."],// 实时展示数据
            newOrders:[], // 最新数据
            orderTotal:0, // 总异常数
            aiTotal:0, // 智能处理数
            cpTotal:0, // CP处理数
            staffTotal:0, // 小二处理数
            stop:false // 实时滚动控制
        }
    }

    // 启动定时任务
    componentDidMount() {
        this.timer = setInterval(() => this.popOrder(), 100);// 订单刷屏时间间隔
        this.timer = setInterval(() => this.queryErrorOrder(), 1000); // 重新加载数据间隔
    }

    //  追加一个数据到页面中
    popOrder(){
        var orderList = this.state.newOrders;
        if(orderList.length>0 && !this.state.stop){
            var orderArray = this.state.orders;
            if(orderArray.length > 17){
                orderArray.shift(); // 去掉第一个
            }
            orderArray.push(orderList.shift().code); // 弹出一个新数据，追加到实时数据末尾
            this.setState({orders: orderArray});// 渲染列表
        }
    }

    // 查询数据
    queryErrorOrder() {
        CommonUtil.ajax({
            url: API.queryErrorOrderList,
            type: 'GET',
        }).then((data) => {
            this.state.newOrders = data.orderList;
            this.state.orderTotal= data.orderTotal;
            this.state.aiTotal= data.aiTotal;
            this.state.cpTotal= data.cpTotal;
            this.state.staffTotal= data.staffTotal;
        });
    }

    // 鼠标移入停止滚动
    handleMouseEnter = (e) => {
        this.setState({
            stop:true
        });
    };
    // 鼠标移出继续滚动
    handleMouseLeave = (e) => {
        this.setState({
            stop:false
        });
    };

    render() {
        let {orders,orderTotal,aiTotal,cpTotal,staffTotal} = this.state;
        return(
            <div className="page-content pam-wrap">
                <Row>
                    <div className="title"><span>关务异常订单处理实时监控</span></div>
                </Row>
                <Row>
                    <div className="orderList" onMouseEnter={this.handleMouseEnter.bind(this)}  onMouseLeave={this.handleMouseLeave.bind(this)} >
                        <ul>
                            {
                                orders.map(function(item){
                                    return <li>{item}</li>
                                })
                            }
                        </ul>
                    </div>
                </Row>
                <Row className="text" >
                    <Col></Col>
                    <Col fixedSpan="10"><span>异常订单数：{orderTotal}</span></Col>
                    <Col fixedSpan="10"><span>智能处理订单数：{aiTotal}</span></Col>
                    <Col fixedSpan="10"><span>CP处理订单数：{cpTotal}</span></Col>
                    <Col fixedSpan="10"><span>小二处理订单数：{staffTotal}</span></Col>
                    <Col></Col>
                </Row>
            </div>
        )
    }
}

ReactDOM.render(<ErrorOrderMonitor/>, document.querySelector('.cgdpl-body'));