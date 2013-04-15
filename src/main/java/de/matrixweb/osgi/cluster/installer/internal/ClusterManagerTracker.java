package de.matrixweb.osgi.cluster.installer.internal;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import de.matrixweb.osgi.cluster.ClusterManager;

/**
 * @author markusw
 */
public class ClusterManagerTracker extends
    ServiceTracker<ClusterManager, ClusterManager> {

  private final ClusterInstaller clusterInstaller;

  /**
   * @param context
   * @param clusterInstaller
   */
  public ClusterManagerTracker(final BundleContext context,
      final ClusterInstaller clusterInstaller) {
    super(context, ClusterManager.class, null);
    this.clusterInstaller = clusterInstaller;
  }

  /**
   * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
   */
  @Override
  public ClusterManager addingService(
      final ServiceReference<ClusterManager> reference) {
    final ClusterManager manager = super.addingService(reference);
    this.clusterInstaller.setManager(manager);
    return manager;
  }

  /**
   * @see org.osgi.util.tracker.ServiceTracker#modifiedService(org.osgi.framework.ServiceReference,
   *      java.lang.Object)
   */
  @Override
  public void modifiedService(final ServiceReference<ClusterManager> reference,
      final ClusterManager service) {
    this.clusterInstaller.setManager(this.context.getService(reference));
  }

  /**
   * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference,
   *      java.lang.Object)
   */
  @Override
  public void removedService(final ServiceReference<ClusterManager> reference,
      final ClusterManager service) {
    this.clusterInstaller.setManager(null);
    super.removedService(reference, service);
  }

}
