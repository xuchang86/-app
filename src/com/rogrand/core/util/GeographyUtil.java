package com.rogrand.core.util;

/**
 * 版权：融贯资讯 <br/>
 * 作者：yong.chen@rogrand.com & xuan.zhou@rogrand.com<br/>
 * 生成日期：2013年11月7日 <br/>
 * 描述：地理工具类
 */
public class GeographyUtil {

    /**
     * EARTH_RADIUS : <地球半径 单位 （千米)>
     */
    private static double EARTH_RADIUS = 6317.137;

    /**
     * 描述：〈将经度或纬度 转换为弧度〉 <br/>
     * 
     * @param lngOrLat 经度或纬度
     * @return 弧度
     */
    private static double rad(double lngOrLat) {
        return lngOrLat * Math.PI / 180;
    }

    /**
     * 描述：〈获取两个坐标点之间的距离〉 <br/>
     * 
     * @param latitude1 A点纬度
     * @param longitude1 A点经度
     * @param latitude2 B点纬度
     * @param longitude2 B点经度
     * @return 两坐标点之间的距离
     */
    public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = (double) (Math.round(s * 100)) / 100;
        return s;
    }

    /**
     * 描述：〈计算指定坐标指定距离的纬度范围〉 <br/>
     * 
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 距离（千米）
     * @return 纬度范围
     */
    public static double[] getLat(double latitude, double longitude, double distance) {
        // 计算纬度下限
        double lowerLat = getLowLat(longitude, latitude, distance);
        // 计算纬度上限
        double higherLat = getHigherLat(longitude, latitude, distance);
        return new double[] { lowerLat, higherLat };
    }

    /**
     * 描述：〈计算指定坐标指定距离的经度范围〉 <br/>
     * 
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 距离（千米）
     * @return 经度范围
     */
    public static double[] getLng(double latitude, double longitude, double distance) {
        // 计算经度下限
        double lowerLng = getLowLng(longitude, latitude, distance);
        // 计算经度上限
        double higherLng = getHigherLng(longitude, latitude, distance);
        return new double[] { lowerLng, higherLng };
    }

    /**
     * 描述：〈计算指定坐标指定距离的经度上限〉 <br/>
     * 
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 距离（千米）
     * @return 经度上限
     */
    private static double getHigherLng(double longitude, double latitude, double distance) {
        double d = EARTH_RADIUS * Math.cos(latitude);
        double radLng = distance / Math.abs(d);
        return longitude + radLng * 180 / Math.PI;
    }

    /**
     * 描述：〈计算指定坐标指定距离的经度下限〉 <br/>
     * 
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 距离（千米）
     * @return 经度下限
     */
    private static double getLowLng(double longitude, double latitude, double distance) {
        double d = EARTH_RADIUS * Math.cos(latitude);
        double radLng = distance / Math.abs(d);
        return longitude - radLng * 180 / Math.PI;
    }

    /**
     * 描述：〈计算指定坐标指定距离的纬度上限〉 <br/>
     * 
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 距离（千米）
     * @return 纬度上限
     */
    private static double getHigherLat(double longitude, double latitude, double distance) {
        double hLat = (Math.sin(Math.abs(latitude) * Math.PI / 180) * EARTH_RADIUS + distance) / EARTH_RADIUS;
        double radHlat = Math.asin(hLat);
        double higherLat = radHlat * 180 / Math.PI;
        return higherLat;
    }

    /**
     * 描述：〈计算指定坐标指定距离的纬度下限〉 <br/>
     * 
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 距离（千米）
     * @return 纬度下限
     */
    private static double getLowLat(double longitude, double latitude, double distance) {
        double lLat = (Math.sin(Math.abs(latitude) * Math.PI / 180) * EARTH_RADIUS - distance) / EARTH_RADIUS;
        double radLlat = Math.asin(lLat);
        return radLlat * 180 / Math.PI;
    }

}
