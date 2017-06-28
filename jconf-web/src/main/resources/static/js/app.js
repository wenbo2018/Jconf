var ajaxHelper = new AjaxHelper()
var vue = new Vue({
    el: '#app',
    data: {
        activeIndex: "2",
        formInline: {
            user: '',
            region: ''
        },
        project:"test",
        dialogFormVisible: false,
        dialogUpdateFormVisible:false,
        deleteDialogVisible:false,
        configDataAdd: {
            key: '',
            value: '',
            configType:0,
            env:[],
            projectName:""
        },
        options: [{
            value: '选项1',
            label: 'Pro'
        }, {
            value: '选项2',
            label: 'Test'
        }, {
            value: '选项3',
            label: 'Dev'
        }],

        currentPage1: 1,

        // 远程搜索框数据
        options4: [],
        value9: [],
        list: [],
        loading: false,
        states: ["Alabama", "Alaska", "Arizona",
            "Arkansas", "California", "Colorado",
            "Connecticut", "Delaware", "Florida",
            "Georgia", "Hawaii", "Idaho", "Illinois",
            "Indiana", "Iowa", "Kansas", "Kentucky",
            "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire",
            "New Jersey", "New Mexico", "New York",
            "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania",
            "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas",
            "Utah", "Vermont", "Virginia",
            "Washington", "West Virginia", "Wisconsin",
            "Wyoming"],
        rules: {
            key: [
                {required: true, message: '请输入配置key', trigger: 'change'}
            ],
            value: [
                {required: true, message: '请输入配置value', trigger: 'change'}
            ],
            env: [
                {required: true, message: '请选择配置环境', trigger: 'change'}
            ]
        },
        apiUrl: '/jconf/admin/config/configPagses',
        tableData: [],
        totalPageSize:0,
        pageSize:0,
        currentTableRow:[],
        currentTableRowIndex:0,


        updateConfigData:{
            id:0,
            key: 'ddd',
            value: 'ddd',
            configType:0,
            env:[],
            projectName:""
        }

    },
    ready: function () {

    },
    mounted() {
        this.list = this.states.map(item => {
                return {value: item, label: item};
    })
        ;
    },
    beforeCreate(){
        //第一次加载page第一页数据
        var vm = this;
        var queryparams={pageIndex:1, pageSize:10};
        callback = function (data) {
            vm.tableData = data.list;
            vm.totalPageSize=data.total;
            vm.pageSize=data.pageSize;
        }
        ajaxHelper.get("/jconf/admin/config/configPagses", queryparams, callback);
    },
    methods: {
        onSearch() {

        },
        //提交表单
        submitForm() {
            var vm = this;
            vm.configDataAdd.projectName=vm.project;
            callback = function (data) {
               if(data.code=="200") {
                   vm.$message({message: '成功创建一条配置', type: 'success'});
                   vm.dialogFormVisible=false;
               } else if (data.code=="500"){
                   vm.$message.error('服务器开小差了');
               } else {
                   vm.$message({showClose: true, message:data.message});
               }
            }
            ajaxHelper.post("/jconf/admin/config/add", vm.configDataAdd, callback);
        },
        updateConfig() {
            var vm = this;
            vm.updateConfigData.projectName=vm.project;
            callback = function (data) {
                if(data.code=="200") {
                    vm.$message({message: '修改配置成功', type: 'success'});
                    vm.dialogUpdateFormVisible=false;
                    vm.currentTableRowIndex=data.datas.configVo;
                } else if (data.code=="500"){
                    vm.$message.error('服务器开小差了');
                } else {
                    vm.$message({showClose: true, message:data.message});
                }
            }
            ajaxHelper.post("/jconf/admin/config/update", vm.updateConfigData, callback);
        },
        updateStatus(index, row) {
            var vm = this;
            var queryparams={id:row.id};
            vm.configDataAdd.projectName=vm.project;
            callback = function (data) {
                if(data.code=="200") {
                    vm.$message({message: '更新配置状态成功', type: 'success'});
                    vm.tableData[index].status=data.datas.configVo.status;
                } else if (data.code=="500"){
                    vm.$message.error('服务器开小差了');
                } else {
                    vm.$message({showClose: true, message:data.message});
                }
            }
            ajaxHelper.post("/jconf/admin/config/updateStatus", queryparams, callback);
        }
        ,
        handleEdit(index, row) {
            var vm = this;
            vm.currentTableRow=row;
            vm.currentTableRowIndex=index;
            vm.updateConfigData=row;
            vm.dialogUpdateFormVisible=true;
        },
        handleDelete(index, row) {
            var vm = this;
            vm.deleteDialogVisible=true;
            vm.currentTableRow=row;
            vm.currentTableRowIndex=index;
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
        ,
        handleDeleteDown() {
            var row=this.currentTableRow;
            var queryparams={id:row.id};
            var index=this.currentTableRowIndex;
            var vm = this;
            callback = function (data) {
                if(data.code=="200") {
                    vm.$message({message: '删除成功!', type: 'success',duration:1000});
                    vm.deleteDialogVisible=false;
                    vm.tableData.splice(index, 1);
                } else if (data.code=="500"){
                    vm.$message.error('服务器开小差了');
                    vm.deleteDialogVisible=false;
                } else {
                    this.$message({showClose: true, message:data.message});
                    vm.deleteDialogVisible=false;
                }
            }
            ajaxHelper.post("/jconf/admin/config/delete",queryparams, callback);
        },
        handleSizeChange(pageSize) {
            console.log("每页 ${val} 条");
        },
        handleCurrentChange(curragePageSize) {
            //第一次加载page第一页数据
            var vm = this;
            var queryparams={pageIndex:curragePageSize, pageSize:10};
            callback = function (data) {
                vm.tableData = data.list;
                vm.totalPageSize=data.total;
                vm.pageSize=data.pageSize;
            }
            ajaxHelper.get(vm.apiUrl, queryparams, callback);
        },
        remoteMethod(query) {
            if (query !== '') {
                this.loading = true;
                setTimeout(() => {
                    this.loading = false;
                this.options4 = this.list.filter(item => {
                        return item.label.toLowerCase()
                            .indexOf(query.toLowerCase()) > -1;
            })
                ;
            },
                200
            )
                ;
            } else {
                this.options4 = [];
            }
        },
    }
});



