package helpers;

import com.jcraft.jsch.*;
import utils.LogUtils;

import java.util.Properties;

public class TransferFileJsch {

    public ChannelSftp setupJsch(String username, String password, String remoteHost){
        JSch jsch = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        Session jschSession = null;
        try {
            jschSession = jsch.getSession(username, remoteHost);
            jschSession.setConfig(config);
            jschSession.setPassword(password);
            jschSession.connect();
            LogUtils.info("Successfully connected to " + remoteHost);
            return (ChannelSftp) jschSession.openChannel("sftp");
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadFileJsch(String username, String password, String remoteHost, String localFile, String remoteDir){
        ChannelSftp channelSftp = setupJsch(username, password, remoteHost);
        try {
            channelSftp.connect();
            channelSftp.put(localFile, remoteDir);
//            LogUtils.info("Successfully update file %s to server %s".formatted(localFile, remoteDir));
        } catch (JSchException | SftpException e) {
            throw new RuntimeException(e);
        }
        channelSftp.exit();
    }

    public void getFileJsch(String username, String password, String remoteHost, String remoteFile, String localDir){
        ChannelSftp channelSftp = setupJsch(username, password, remoteHost);
        try {
            channelSftp.connect();
            channelSftp.get(remoteFile, localDir);
//            LogUtils.info("Successfully get file %s to %s".formatted(remoteFile, localDir));
        } catch (JSchException | SftpException e) {
            throw new RuntimeException(e);
        }
        channelSftp.exit();
    }

    public void removeFileJsch(String username, String password, String remoteHost, String remoteFile){
        ChannelSftp channelSftp = setupJsch(username, password, remoteHost);
        try {
            channelSftp.connect();
            channelSftp.rm(remoteFile);
//            LogUtils.info("Successfully remove file %s".formatted(remoteFile));
        } catch (JSchException | SftpException e) {
            throw new RuntimeException(e);
        }
        channelSftp.exit();
    }

}
