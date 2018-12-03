package com.ali.dao;

import com.ali.entity.Option;
import com.ali.config.MutipleSqlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class Master2016Dao{
    @Autowired
//    SQLManager master2016SQLManager;
    MutipleSqlManager mutipleSqlManager;

    public List<Map> get教师列表() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师列表", Map.class);
    }

    public List<Map> get学科平台列表() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取学科平台列表", Map.class);
    }

    public List<Map> get教师授课信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师授课信息", Map.class, paras);
    }

    public List<Map> get教师论文信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师论文信息", Map.class, paras);
    }

    public List<Map> get教师专利信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师专利信息", Map.class, paras);
    }

    public List<Map> get教师项目信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师项目信息", Map.class, paras);
    }

    public List<Map> get教师奖励信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师奖励信息", Map.class, paras);
    }

    public List<Map> get教师专著信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师专著信息", Map.class, paras);
    }

    public List<Map> get教师教材信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师教材信息", Map.class, paras);
    }

    public List<Map> get教师成果信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师成果信息", Map.class, paras);
    }

    public List<Map> get教师个人信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师个人信息", Map.class, paras);
    }

    public List<Map> get高层次人才统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业高层次人才统计",Map.class);
    }

    public List<Map> get师资结构统计(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业师资结构统计",Map.class);
    }

    public List<Map> get专职教师统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("master2016.分专业专职教师统计",Map.class);
    }

    public List<Map> get教学团队统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教学团队统计",Map.class);
    }

    public List<Map> get生源统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("master2016.分专业生源统计",Map.class);
    }

    public List<Map> get留学生统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业留学生统计",Map.class);
    }

    public List<Map> get学生交流统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业学生交流统计",Map.class);
    }

    public List<Map> get毕业生统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业毕业生统计",Map.class);
    }

    public List<Map> get获奖统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教师获得科研奖励统计",Map.class);
    }

    public List<Map> get教师专利授权统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教师专利授权统计",Map.class);
    }

    public List<Map> get教师主编专业教材统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教师主编专业教材统计",Map.class);
    }

    public List<Map> get教师主持科研项目统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教师主持科研项目统计",Map.class);
    }

    public List<Map> get教师出版专著统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教师出版专著统计",Map.class);
    }

    public List<Map> get教师发表论文统计() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业教师发表论文统计",Map.class);
    }

    public List<Map> get校内专业列表() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.校内专业列表",Map.class);
    }

    public List<Map> get专业基本信息(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业基本信息", Map.class, paras);
    }

    public List<Map> get专业内教职工列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内教职工列表", Map.class, paras);
    }

    public List<Map> get专业内教学成果列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内教学成果列表", Map.class, paras);
    }

    public List<Map> get专业内开设课程列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内开设课程列表", Map.class, paras);
    }

    public List<Map> get专业内实习基地列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内实习基地列表", Map.class, paras);
    }

    public List<Map> get专业内本科生列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内本科生列表", Map.class, paras);
    }

    public List<Map> get专业内各类竞赛列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内各类竞赛列表", Map.class, paras);
    }

    public List<Map> get专业内专业比赛列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内专业比赛列表", Map.class, paras);
    }

    public List<Map> get专业内学术论文列表(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业内学术论文列表", Map.class, paras);
    }

    public List<Map> get学生情况(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业学生情况", Map.class, paras);
    }

    public List<Map> get在校成果(Map<String, Object> paras) {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.分专业在校成果", Map.class, paras);
    }

    public List<Option> get专业技术职称指标类型() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取专业技术职称指标",Option.class);
    }

    public List<Option> get高层次人才指标类型() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取高层次人才指标",Option.class);
    }

    public List<Option> get教师情况指标类型() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取教师情况指标",Option.class);
    }

    public List<Option> get学历情况指标类型() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取学历情况指标",Option.class);
    }

    public List<Option> get最高学位指标类型() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取最高学位指标",Option.class);
    }

    public List<Option> get高层次研究团队指标类型() {
        return mutipleSqlManager.getMaster2016SQLManager().select("common.获取高层次研究团队指标",Option.class);
    }

    public List<Map> get专业技术职称指标趋势统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业技术职称指标趋势统计",Map.class,paras);
    }

    public List<Map> get专业技术职称指标对比统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.专业技术职称指标对比统计",Map.class,paras);
    }

    public List<Map> get教师情况指标趋势统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.教师情况指标趋势统计",Map.class,paras);
    }

    public List<Map> get教师情况指标对比统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.教师情况指标对比统计",Map.class,paras);
    }

    public List<Map> get学历情况指标趋势统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.学历情况指标对比统计",Map.class,paras);
    }

    public List<Map> get学历情况指标对比统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.学历情况指标对比统计",Map.class,paras);
    }

    public List<Map> get最高学位指标趋势统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.最高学位指标趋势统计",Map.class,paras);
    }

    public List<Map> get最高学位指标对比统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.最高学位指标对比统计",Map.class,paras);
    }

    public List<Map> get高层次人才指标趋势统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.高层次人才指标趋势统计",Map.class,paras);
    }

    public List<Map> get高层次人才指标对比统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.高层次人才指标对比统计",Map.class,paras);
    }

    public List<Map> get高层次研究团队指标趋势统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.高层次研究团队指标趋势统计",Map.class,paras);
    }

    public List<Map> get高层次研究团队指标对比统计(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.高层次研究团队指标对比统计",Map.class,paras);
    }

    public List<Map> get高校情况(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况",Map.class);
    }

    public List<Map> getDZDWDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况党政单位钻取",Map.class);
    }

    public List<Map> getJXDWDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况教学单位钻取",Map.class);
    }

    public List<Map> getZDXKDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况重点学科钻取",Map.class);
    }

    public List<Map> getZXSSDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况在校生数钻取",Map.class);
    }

    public List<Map> getJZGZGXWDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况教职工总数最高学位钻取",Map.class);
    }

    public List<Map> getJZGZYJSZCDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况教职工总数专业技术职称钻取",Map.class);
    }

    public List<Map> getZZDMJDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页高校情况总占地面积钻取",Map.class);
    }

    public List<Map> get教学成果(){
        return mutipleSqlManager.getMaster2016SQLManager().select("master2016.首页教学成果",Map.class);
    }

    public List<Map> getGJJJYJXYJYGGXMDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页教学成果国家级教育教学研究与改革项目钻取",Map.class);
    }

    public List<Map> getSJBKJXGCDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页教学成果省级本科教学工程钻取",Map.class);
    }

    public List<Map> getGJJBKJXGCDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页教学成果国家级本科教学工程钻取",Map.class);
    }

    public List<Map> getQTJSGJJSJYRCXCYJDDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页教学成果牵头建设国家级实践育人创新创业基地钻取",Map.class);
    }

    public List<Map> getRCPYMSCXSYQDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页教学成果人才培养模式创新实验区钻取",Map.class);
    }

    public List<Map> getGJJJXJDDrillData(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页教学成果国家级教学基地钻取",Map.class);
    }

    public List<Map> get科研水平(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页科研水平",Map.class,paras);
    }

    public List<Map> getZLZZQDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页科研水平专利著作权钻取",Map.class,paras);
    }

    public List<Map> getCBZZDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页科研水平出版专著钻取",Map.class,paras);
    }

    public List<Map> getFBLWDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页科研水平发表论文钻取",Map.class,paras);
    }

    public List<Map> getZXXMDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页科研水平纵向项目钻取",Map.class,paras);
    }

    public List<Map> getHXXMDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页科研水平横向项目钻取",Map.class,paras);
    }

    public List<Map> get办学条件(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页办学条件",Map.class);
    }

    public List<Map> get学生情况(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学生情况",Map.class);
    }

    public List<Map> get师资情况(){
        return mutipleSqlManager.getMaster2016SQLManager().select("master2016.首页师资情况",Map.class);
    }

    public List<Map> getGCCRCDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页师资情况高层次人才钻取",Map.class,paras);
    }

    public List<Map> get学科专业(){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业",Map.class);
    }

    public List<Map> getKSZYDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业开设专业钻取",Map.class,paras);
    }

    public List<Map> getFGXKMLDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业覆盖学科门类钻取",Map.class,paras);
    }

    public List<Map> getBSXWSQYJXKDDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业博士学位授权一级学科点钻取",Map.class,paras);
    }

    public List<Map> getBSXWSQEJXKDDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业博士学位授权二级学科点钻取",Map.class,paras);
    }

    public List<Map> getSSXWSQYJXKDDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业硕士学位授权一级学科点钻取",Map.class,paras);
    }

    public List<Map> getSSXWSQEJXKDDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业硕士学位授权二级学科点钻取",Map.class,paras);
    }

    public List<Map> getBSHLDZDrillData(Map<String,Object> paras){
        return mutipleSqlManager.getMaster2016SQLManager().select("common.首页学科专业博士后流动站钻取",Map.class,paras);
    }

}
