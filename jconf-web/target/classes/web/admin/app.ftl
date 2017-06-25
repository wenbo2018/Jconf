<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/index.css"/>
</head>
<body style="margin: 0px;">

<div id="app">
    <!--导航配置栏-->
    <el-menu theme="dark" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1"><span style="font-size:x-large;" >Jconf配置管理平台</span></el-menu-item>
        <el-menu-item index="2"><span style="font-size:larger;">配置管理</span></el-menu-item>
        <el-menu-item index="3"><span style="font-size:larger;">系统监控</span></el-menu-item>
        <el-menu-item index="4"><span style="font-size:larger;">权限管理</span></el-menu-item>
        <el-menu-item index="5"><span style="font-size:larger;">系统管理</span></el-menu-item>
    </el-menu>
    <div class="line" style="margin-top: 20px;"></div>


    <!--编辑搜索栏-->
    <div style="width: 90%;margin-left:5%;margin-bottom: 1%;">
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="Key" style="font-size:larger;">
                <template>
                    <el-select v-model="value9"    multiple filterable="" remote placeholder="根据key或者项目名称搜索"
                               :remote-method="remoteMethod" :loading="loading" style="width:400px;">
                        <el-option v-for="item in options4" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </template>
            </el-form-item>
            <el-form-item>
                <el-button type="success" @click="onSearch" icon="search">搜索</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="dialogFormVisible = true">新增配置</el-button>
            </el-form-item>
        </el-form>
    </div>


    <!--新增配置弹出页-->
    <el-dialog title="新增配置" :visible.sync="dialogFormVisible">
        <el-form :model="configDataAdd" :rules="rules" ref="configDataAdd" label-width="100px" class="demo-ruleForm">

            <el-form-item label="Key" prop="configKey" required>
                <el-input v-model="configDataAdd.configKey"></el-input>
            </el-form-item>

            <el-form-item label="Value" prop="configValue" required>
                <el-input type="textarea" v-model="configDataAdd.configValue"></el-input>
            </el-form-item>

            <el-form-item label="类型" prop="configType">
                <el-select v-model="configDataAdd.configType" placeholder="请选择类型">
                    <el-option label="String" value="1"></el-option>
                    <el-option label="Integer" value="2"></el-option>
                    <el-option label="Boolean" value="3"></el-option>
                </el-select>
            </el-form-item>


            <el-form-item label="配置环境" prop="configEnvironment" required>
                <el-checkbox-group v-model="configDataAdd.configEnvironment">
                    <el-checkbox label="1" name="configEnvironment">Dev/开发环境</el-checkbox>
                    <el-checkbox label="2" name="configEnvironment">Beta/测试环境</el-checkbox>
                    <el-checkbox label="3" name="configEnvironment">Pro/线上环境</el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('configDataAdd')">立即创建</el-button>
                <el-button @click="resetForm('configDataAdd')">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>



    <!--修改配置弹出页-->
    <el-dialog title="修改配置" :visible.sync="dialogUpdateFormVisible">
        <el-form :model="updateConfigData"  :rules="rules" ref="updateConfigData" label-width="100px" class="demo-ruleForm">
            <el-form-item label="Key" prop="configKey" required>
                <el-input v-model="updateConfigData.configKey" value="ddd"></el-input>
            </el-form-item>
            <el-form-item label="Value" prop="updateConfigData" required>
                <el-input type="textarea" v-model="updateConfigData.configValue"></el-input>
            </el-form-item>
            <el-form-item label="类型" prop="configType">
                <el-select v-model="updateConfigData.configType" placeholder="请选择类型">
                    <el-option label="String" value="1"></el-option>
                    <el-option label="Integer" value="2"></el-option>
                    <el-option label="Boolean" value="3"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="配置环境" prop="configEnvironment" required>
                <el-checkbox-group v-model="updateConfigData.configEnvironment">
                    <el-checkbox label="1" name="configEnvironment">Dev/开发环境</el-checkbox>
                    <el-checkbox label="2" name="configEnvironment">Beta/测试环境</el-checkbox>
                    <el-checkbox label="3" name="configEnvironment">Pro/线上环境</el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('updateConfigData')">立即修改</el-button>
                <el-button @click="resetForm('updateConfigData')">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>




    <!--table组件-->
    <template>
        <el-table :data="tableData" border style="width: 90%;margin-left:5%;" >
            <el-table-column label="#" width="80" align="center">
                <template scope="scope">
                    <span>{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="Key"  align="center">
                <template scope="scope">
                    <span >{{ scope.row.key}}</span>
                </template>
            </el-table-column>
            <el-table-column label="Value" width="80" align="center">
                <template scope="scope">
                    <el-popover trigger="hover" placement="top" align="center">
                        <p>Value: {{ scope.row.value }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag>查看</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column label="类型" width="90" align="center">
                <template scope="scope">
                    <span>String</span>
                </template>
            </el-table-column>

            <el-table-column label="环境" width="150" align="center">
                <template scope="scope">
                    <div v-for="env in scope.row.env.split(',')" style="float: left;margin-left:1px;">
                        <span v-show="env==1"><el-tag color="FF3300" type="success">Pro</el-tag></span>
                        <span v-show="env==2"><el-tag type="gray">Beta</el-tag></span>
                        <span v-show="env==3"><el-tag type="primary">Dev</el-tag></span>
                    </div>
                </template>
            </el-table-column>

            <el-table-column label="负责人" width="130" align="center">
                <template scope="scope">
                    <span>{{scope.row.userName}}</span>
                </template>
            </el-table-column>

            <el-table-column label="修改时间" width="180" align="center">
                <template scope="scope">
                    <span>{{ scope.row.dateTime}}</span>
                </template>
            </el-table-column>

            <el-table-column label="历史版本" width="100" align="center">
                <template scope="scope">
                    <span>1.0.0</span>
                </template>
            </el-table-column>

            <el-table-column label="状态" width="140" align="center">
                <template scope="scope">
                    <el-button size="small" type="success">启用</el-button>
                    <el-button size="small" >停止</el-button>
                </template>
            </el-table-column>

            <el-table-column label="操作" width="140" align="center">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>

    <!--分页组件-->
    <div class="page" border style="width: 90%;margin-left:5%;margin-top: 1%;padding: 0;">
        <div class="block" style="padding-left: 0%;">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage1"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="pageSize"
                    layout="sizes, prev, pager, next"
                    :total="totalPageSize">
            </el-pagination>
        </div>
    </div>


    <el-dialog title="提示" :visible.sync="deleteDialogVisible" size="tiny" :before-close="handleClose">
        <span>确定要删除?删除前请确认业务方下线该配置!</span>
            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleDeleteDown">确 定</el-button>
      </span>
    </el-dialog>


</div>
</body>
<script src="/js/vue.js"></script>
<script src="/js/index.js"></script>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/ajax-helper.js"></script>
<script src="/js/app.js"></script>
</html>