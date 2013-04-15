package de.matrixweb.osgi.cluster.installer.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author markusw
 */
public class Activator implements BundleActivator {

  private ClusterInstaller installer;

  private ClusterManagerTracker clusterManagerTracker;

  private InstallerTracker installerTracker;

  /**
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(final BundleContext context) {
    this.installer = new ClusterInstaller();
    this.clusterManagerTracker = new ClusterManagerTracker(context,
        this.installer);
    this.clusterManagerTracker.open();
    this.installerTracker = new InstallerTracker(context, this.installer);
    this.installerTracker.open();
  }

  /**
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(final BundleContext context) {
    if (this.installerTracker != null) {
      this.installerTracker.close();
      this.installerTracker = null;
    }
    if (this.clusterManagerTracker != null) {
      this.clusterManagerTracker.close();
      this.clusterManagerTracker = null;
    }
  }

}
