package de.matrixweb.osgi.cluster.installer.internal;

import de.matrixweb.osgi.cluster.ClusterManager;
import de.matrixweb.osgi.kernel.maven.Installer;

/**
 * @author markusw
 */
public class ClusterInstaller {

  private ClusterManager manager;

  private Installer installer;

  /**
   * @param manager
   *          the manager to set
   */
  public void setManager(final ClusterManager manager) {
    this.manager = manager;
  }

  /**
   * @param installer
   *          the installer to set
   */
  public void setInstaller(final Installer installer) {
    this.installer = installer;
  }

}
